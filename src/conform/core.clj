(ns conform.core
  (:require [instaparse.core :as insta])
  (:require clojure.pprint)
  (:gen-class))

(def parser
  (insta/parser (clojure.java.io/resource "postgresql94.bnf") :string-ci true))

(defn -main
  "I don't do a whole lot."
  []
  (clojure.pprint/pprint (parser (slurp "SAMPLE.sql"))))
