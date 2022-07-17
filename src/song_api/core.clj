(ns song-api.core
  (:use [clojure.pprint])
  (:require [song-api.db.datomic.schema :as schema]
            [song-api.db.datomic.entities :as entities]
            [song-api.db.datomic.repositories.author-repository :as author-repository]))

(def connection (schema/open-connection!))

(schema/create-schema! connection)

(author-repository/insert-author! connection
                                  (entities/create-author "50 cent" 50))


(pprint (author-repository/get-authors! connection))
(def authors (author-repository/get-authors! connection))

(pprint (author-repository/get-author-by-id! connection (:author/id (first authors))))

(pprint (author-repository/delete-author! connection (:author/id (first authors))))
