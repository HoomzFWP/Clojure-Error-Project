(ns babel.new-test
  (:require [clojure.tools.nrepl :as repl])
  (:use [clojure.test]))

(def server-port 7888)

;;gets the returning message from open repl
(defn trap-response
  "evals the code given as a string, and returns the list of associated nREPL messages"
  [inp-code]
  (with-open [conn (repl/connect :port server-port)]
    (-> (repl/client conn 1000)
        (repl/message {:op :eval :code inp-code})
        doall)))

(deftest test001
  (is "Tried to aaadivide by zero\n" (trap-response "(/ 70 0)")))

;;(use 'clojure.test)
;;(require '[clojure.tools.nrepl :as repl])
