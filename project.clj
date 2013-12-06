(defproject nataliedee "0.1.0-SNAPSHOT"
  :description "Natalie Dee Mobile site"
  :url "http://example.com/FIXME"
  ;; :jvm-opts ["-Xmx1g"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2080"]
                 [compojure "1.1.5"]
                 [hiccup "1.0.4"]
                 [enlive "1.0.0"]
                 [ring-server "0.3.0"]
                 [jayq "2.5.0"]]
  :plugins [[lein-ring "0.8.7"]
            [lein-cljsbuild "1.0.1-SNAPSHOT"]]
  :ring {:handler nataliedee.handler/app
         :init nataliedee.handler/init
         :destroy nataliedee.handler/destroy}
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.0"]]}}

  :cljsbuild {
    :builds [{
        ; The path to the top-level ClojureScript source directory:
        :source-paths ["src-cljs"]
        ; The standard ClojureScript compiler options:
        ; (See the ClojureScript compiler documentation for details.)
        :compiler {
          :output-to "resources/public/js/main.js"  ; default: target/cljsbuild-main.js
          :optimizations :simple
          :pretty-print false
          :externs ["externs/jquery.js"]}}]})
