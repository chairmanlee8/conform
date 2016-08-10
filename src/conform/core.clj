(ns conform.core
  (:require [instaparse.core :as insta])
  (:require [clojure.core.match :refer [match]])
  (:require clojure.pprint)
  (:gen-class))

(defrecord Database [tables])
(defrecord Table [identifier columns])
(defrecord Column [identifier typeString constraints])

(def parser
  (insta/parser (clojure.java.io/resource "postgresql94.bnf") :string-ci true))

(defn -main
  "I don't do a whole lot."
  []
  (clojure.pprint/pprint (parser (slurp "A.sql"))))

(defn build-database
  "Generate database model from parse tree."
  [p]
  (match p
    [:statements & r] ()
    :else (throw (Exception. "Invalid parse tree, expected root element to be :statements."))))

(defn dispatch-statement "" [] 0)

(defn process-create-table
  "Process :createTable node and apply changes to model."
  [model statement]
  (match statement
    [:createTable [:identifier id] [:columns cols]] (0))
    :else (throw (Exception. "Unexpected :createTable structure.")))

(defn process-column
  "Process :column node, returns Column record"
  []
  0)

(defn generate-type-string
  "Generate a canonical SQL type spec from a :dataType node."
  [statement]
  (match statement
    [:dataType [:smallInt]] "SMALLINT"
    [:dataType [:integer]] "INT"
    [:dataType [:bigInt]] "BIGINT"
    [:dataType [:decimal]] "DECIMAL"
    [:dataType [:decimal [:precisionScale a]]] (str "DECIMAL(" a ")")
    [:dataType [:decimal [:precisionScale a b]]] (str "DECIMAL(" a "," b ")")
    [:dataType [:numeric]] "NUMERIC"
    [:dataType [:numeric [:precisionScale a]]] (str "NUMERIC(" a ")")
    [:dataType [:numeric [:precisionScale a b]]] (str "NUMERIC(" a "," b ")")
    :else (throw (Exception. "Unhandled data type."))))
