(defproject nataliedee "0.1.0-SNAPSHOT"
  :description "Natalie Dee Mobile site"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.4"]
                 [enlive "1.0.0"]
                 [ring-server "0.3.0"]]
  :plugins [[lein-ring "0.8.7"]]
  :ring {:handler nataliedee.handler/app
         :init nataliedee.handler/init
         :destroy nataliedee.handler/destroy}
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.0"]]}})
