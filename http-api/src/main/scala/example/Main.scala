import cats.effect._
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.ember.server._
import org.http4s.circe._
import io.circe.generic.auto._
import org.http4s.implicits._
import org.http4s.circe.CirceEntityCodec._
import com.comcast.ip4s._

object Main extends IOApp.Simple {

  case class Item(id: Int, name: String)

  def routes(ref: Ref[IO, List[Item]]): HttpRoutes[IO] =
    HttpRoutes.of[IO] {

      case GET -> Root / "items" =>
        ref.get.flatMap(items => Ok(items))

      case req @ POST -> Root / "items" =>
        req.as[Item].flatMap { item =>
          ref.update(_ :+ item) *> Created(Map("message" -> s"Item ${item.id} ajouté"))

        }
    }

  def run: IO[Unit] =
    for {
      ref <- Ref.of[IO, List[Item]](List.empty)
      _ <- EmberServerBuilder
        .default[IO]
        .withHost(host"localhost")
        .withPort(port"8080")
        .withHttpApp(routes(ref).orNotFound)
        .build
        .useForever
    } yield ()
}
