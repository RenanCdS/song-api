(ns song-api.controllers.author
  (:require [song-api.db.datomic.repositories.author-repository :as author-repository]))

(defn get-authors! [connection]
  (map (fn [author] (assoc author :author/id (.toString (:author/id author)))) (author-repository/get-authors! connection)))
