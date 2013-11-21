(ns nataliedee.server
  (:use ring.adapter.jetty)
  (:use nataliedee.handler))

;; Our fancy -main for heroku
(defn -main [& args]
	(let [port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (run-jetty app {:port port})))
