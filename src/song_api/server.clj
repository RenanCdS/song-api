(ns song-api.server
  (:require [io.pedestal.http :as http]
            [io.pedestal.interceptor :as i]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.content-negotiation :as conneg]
            [clojure.data.json :as json]
            [song-api.controllers.author :as controller.author]
            [song-api.db.datomic.schema :as schema]))

(defn include-connection-interceptor []
  (i/interceptor {
                  :name :include-connection
                  :enter (fn [context]
                           (assoc context :connection (schema/open-connection!)))
                  }))

(defn get-authors []
  (i/interceptor
    {:name :get-authors
     :enter (fn [context]
              (let [authors (controller.author/get-authors! (:connection context))
                    authors (json/write-str authors)]
                (assoc context :response {:status 200 :body authors :headers {"Content-Type" "application/json"}})))}))

(def content-negotiation (conneg/negotiate-content "application/json" "application/edn"))
(def routes
  (route/expand-routes
    #{["/api/author" :get (conj [
                                 content-negotiation
                                 (include-connection-interceptor)] (get-authors))
                      :route-name :get-authors]}))

(defn create-server! []
  (http/create-server
    {::http/routes routes
     ::http/type :jetty
     ::http/port 8890}))

(defn start []
  (http/start (create-server!)))