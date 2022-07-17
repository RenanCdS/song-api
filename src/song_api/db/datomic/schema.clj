(ns song-api.db.datomic.schema
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/song-api")

(defn open-connection!
  []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn delete-database!
  []
  (d/delete-database db-uri))


(def schema
  [
   ;; Song
   {:db/ident       :song/id
    :db/valueType   :db.type/uuid
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity
    :db/doc         "Id of the song"}

   {:db/ident       :song/title
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Title of the song"}

   {:db/ident       :song/released-at
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Release date of the song"}

   {:db/ident       :song/author
    :db/valueType   :db.type/ref
    :db/cardinality :db.cardinality/one
    :db/doc         "Author of the song"}

   {:db/ident       :song/duration
    :db/valueType   :db.type/long
    :db/cardinality :db.cardinality/one
    :db/doc         "Duration in seconds of the song"}

   {:db/ident       :song/genre
    :db/valueType   :db.type/ref
    :db/cardinality :db.cardinality/many
    :db/doc         "Genre of the song"}

   ;; Author
   {:db/ident       :author/id
    :db/valueType   :db.type/uuid
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity
    :db/doc         "Id of the author"}

   {:db/ident       :author/name
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Name of the author"}

   {:db/ident       :author/age
    :db/valueType   :db.type/long
    :db/cardinality :db.cardinality/one
    :db/doc         "Age of the author"}

   ;; Genre
   {:db/ident       :genre/id
    :db/valueType   :db.type/uuid
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity
    :db/doc         "Id of the genre"}

   {:db/ident       :genre/name
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Name of the genre"}
   ])

(defn create-schema!
  [connection]
  (d/transact connection schema))


