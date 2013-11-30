(ns nataliedee.routes.home
  (:require [compojure.core :refer :all]
            [nataliedee.routes.random :as random]
            [nataliedee.views.layout :as layout]
            [hiccup.element :refer [image unordered-list]])
  (:use net.cgrand.enlive-html)
  (:import java.net.URL))

(defn home []
  (layout/common
    [:div.images (unordered-list (random/nd-template))]))

(defroutes home-routes
  (GET "/" [] (home)))
