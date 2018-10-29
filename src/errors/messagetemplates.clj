(ns errors.messagetemplates)
(require '[clojure.string :as cs])

(defn combineargs [x]
  (cs/replace (cs/replace (cs/replace (str x) #"  " " ") "(" "") ")" ""))

(defn you-cannot [x & args]
  (if args (str "You cannot " x ", " (combineargs args) ".\n")
          (str "You cannot " x ".\n")))
