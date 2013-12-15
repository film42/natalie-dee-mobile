(ns nataliedee.routes.proxy
  (:require [compojure.core :refer :all]
            [nataliedee.views.random :as layout]
            [hiccup.element :refer [image unordered-list]]
            [ring.util.response :as response]
            [hiccup.page :refer [html5]])
  (:use net.cgrand.enlive-html)
  (:import java.net.URL))

(defn -get-input-stream-for-url [url]
  (-> (java.net.URL. url)
      .openConnection
      (doto (.setRequestProperty
             "User-Agent"
             "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36"))
      .getContent))

(defn -get-proxy-ua [url]
  (let [inputstream (-get-input-stream-for-url url)]
    (-> (response/response inputstream)
        (response/header "Content-Length" 2342334))))

(defroutes proxy-routes
  (GET "/proxy" []
    {:body (-get-proxy-ua "http://codingrockets.com/site_archive/current/images/sites/dubclub.jpg")}))
