(ns nataliedee.routes.random
  (:require [compojure.core :refer :all]
            [nataliedee.views.random :as layout]
            [hiccup.element :refer [image unordered-list]]
            [hiccup.page :refer [html5]])
  (:use net.cgrand.enlive-html)
  (:import java.net.URL))

(defn -get-random-comics-ua []
  (with-open [inputstream (-> (java.net.URL. "http://www.nataliedee.com/index.php")
                            .openConnection
                            (doto (.setRequestProperty "User-Agent"
                                                       "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36"))
                            .getContent)]

  (html-resource inputstream)))

(defn get-random-comics []
  (-> (-get-random-comics-ua)
    (select [:div.arcDayComic :img.comic])))

(defn nd-template []
  (let [images (vec (get-random-comics))]
    (for [i (range (count images))]
      (image (-> (images i) :attrs :src)))))

(defn add-proxy [x]
  (str "http://imageproxy.heroku.com/convert?source=" x))

(defn random []
  (layout/template [:div (-get-random-comics-ua)] (unordered-list (map add-proxy (nd-template)))))

(defroutes random-routes
  (GET "/random" [] (random)))
