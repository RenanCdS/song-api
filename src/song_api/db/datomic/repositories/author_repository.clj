(ns song-api.db.datomic.repositories.author-repository
  (:require [schema.core :as s]
            [datomic.api :as d]
            [song-api.db.datomic.entities :as entities]))

(s/defn insert-author!
  [connection, author :- entities/Author]
  (d/transact connection [author]))

(s/defn get-authors! :- [entities/Author]
  [connection]
  (d/q '[:find  [(pull ?authorid [*]) ...]
         :where [?authorid :author/id]]
       (d/db connection)))

(s/defn get-author-by-id! :- entities/Author
  [connection, authorid]
  (d/q '[:find [(pull ?author [*])]
         :in $ ?id
         :where [?author :author/id ?id]]
       (d/db connection) authorid))

;; Lookup ref version
(s/defn get-author-by-id! :- entities/Author
  [connection, authorid :- java.util.UUID]
  (d/pull (d/db connection) '[*] [:author/id authorid]))

(s/defn delete-author!
  [connection, authorid :- java.util.UUID]
  (d/transact connection [[:db/retractEntity [:author/id authorid]]]))

