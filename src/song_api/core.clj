(ns song-api.core
  (:use [clojure.pprint])
  (:require [song-api.server :as server]))

(defn -main []
  (server/start))
