(ns song-api.models.genre
  (:require [schema.core :as s]))

(def Genre
  {:genre/id        java.util.UUID
   :genre/name      s/Str})