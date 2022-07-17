(ns song-api.models.author
  (:require [schema.core :as s]))

(def Author
  {:author/id       java.util.UUID
   :author/name     s/St
   :author/age      s/Int})