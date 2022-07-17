(ns song-api.models.song
  (:require [schema.core :as s]))

(def Song
  {:song/id           java.util.UUID
   :song/title        s/Str
   :song/released-at  s/Str
   :song/author       Author
   :song/duration     s/Num
   :song/genre        Genre})