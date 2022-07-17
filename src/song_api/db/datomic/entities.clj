(ns song-api.db.datomic.entities
  (:require [schema.core :as s]))

(def Genre
  {:genre/id                          java.util.UUID
   (s/optional-key  :genre/name)      s/Str})

(def Author
  {:author/id       java.util.UUID
   (s/optional-key :author/name)     s/Str
   (s/optional-key :author/age)      s/Int})

(def Song
  {:song/id           java.util.UUID
   (s/optional-key :song/title)        s/Str
   (s/optional-key :song/released-at)  s/Str
   (s/optional-key :song/author)       Author
   (s/optional-key :song/duration)     s/Num
   (s/optional-key :song/genre)        Genre})

(s/defn create-author :- Author
  [name :- s/Str, age :- s/Int]
  {:author/id     (java.util.UUID/randomUUID)
   :author/name   name
   :author/age    age})