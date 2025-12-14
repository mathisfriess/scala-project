# Projet API HTTP Scala

Ce projet est un petit serveur HTTP écrit en **Scala**, utilisant **Cats Effect** et **http4s**. Il expose une API REST très simple avec des requêtes **GET** et **POST**, sans base de données : les données sont stockées uniquement en mémoire.

---

## Fonctionnalités

* Serveur HTTP
* API REST
* Endpoint GET pour lire les données
* Endpoint POST pour ajouter des données
* Stockage en mémoire (pas de base de données)
* Échange de données en JSON

---

## Prérequis

Avant de lancer le projet, il faut avoir :

* **Java 11 ou 17** installé
* **sbt** installé (outil de build Scala)

Pour vérifier :

```bash
java --version
sbt --version
```

---

## Lancer le projet

1. Cloner ou télécharger le projet

2. Se placer à la racine du projet :

```bash
cd http-api
```

3. Lancer le serveur :

```bash
sbt run
```

Si tout se passe bien, le serveur démarre et écoute sur :

```
http://localhost:8080
```

---

## Utilisation de l'API

### Ajouter un item (POST)

```bash
curl -X POST http://localhost:8080/items \
-H "Content-Type: application/json" \
-d '{"id":1,"name":"test"}'
```

Réponse attendue :

```
Item ajouté : test
```

---

### Récupérer les items (GET)

```bash
curl http://localhost:8080/items
```

Exemple de réponse :

```json
[
  {"id":1,"name":"test"}
]
```

---

## Remarques

* Les données sont perdues à l'arrêt du serveur (stockage en mémoire)
* Le projet est volontairement simple
* Il n'y a pas de base de données

---

## Technologies utilisées

* Scala
* Cats Effect
* http4s
* Circe (JSON)

---
