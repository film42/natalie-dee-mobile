(ns nataliedee.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [nataliedee.routes.home :refer [home-routes]]
            [nataliedee.routes.archive :refer [archive-routes]]))

(defn init []
  (println "nataliedee is starting"))

(defn destroy []
  (println "nataliedee is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes home-routes archive-routes app-routes)
      (handler/site)
      (wrap-base-url)))


