(ns conform.core
  (:require [instaparse.core :as insta])
  (:gen-class))

(def parser
  (insta/parser (clojure.java.io/resource "postgresql94.bnf")))

(defn -main
  "I don't do a whole lot."
  []
  (println (parser (slurp "SAMPLE.sql"))))
