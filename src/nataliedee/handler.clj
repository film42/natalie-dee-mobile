(ns nataliedee.handler
  (:require [compojure.core :refer [defroutes routes]]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.file-info :refer [wrap-file-info]]
            [ring.middleware.file :refer [wrap-file]]
            [hiccup.middleware :refer [wrap-base-url]]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [nataliedee.routes.home :refer [home-routes]]
            [nataliedee.routes.proxy :refer [proxy-routes]]
            [nataliedee.routes.random :refer [random-routes]]
            [nataliedee.routes.archive :refer [archive-routes]]))

(defn init []
  (println "nataliedee is starting"))

(defn destroy []
  (println "nataliedee is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes home-routes random-routes archive-routes proxy-routes app-routes)
      (handler/site)
      (wrap-base-url)))


