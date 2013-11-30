(ns nataliedee.routes.random
  (:require [compojure.core :refer :all]
            [nataliedee.views.random :as layout]
            [hiccup.element :refer [image unordered-list]]
            [hiccup.page :refer [html5]])
  (:use net.cgrand.enlive-html)
  (:import java.net.URL))

(defn get-random-comics []
  (-> "http://www.nataliedee.com/index.php" URL. html-resource
    (select [:div.arcDayComic :img.comic])))

(defn nd-template []
  (let [images (vec (get-random-comics))]
    (for [i (range (count images))]
      (image (-> (images i) :attrs :src)))))

(defn random []
  (layout/template (unordered-list (nd-template))))

(defroutes random-routes
  (GET "/random" [] (random)))
