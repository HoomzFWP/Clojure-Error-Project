(ns babel.processor
  (:require  [clojure.tools.nrepl :as repl]
             [clojure.spec.test.alpha :as stest]
             [errors.messageobj :as m-obj]
             [errors.prettify-exception :as p-exc]
             [corefns.corefns :as cf]
             [corefns.instrumentfunctionsfortesting]
             [clojure.tools.nrepl.middleware.session :refer [session]]))

;;an atom that record original error response
(def recorder (atom {:msg [] :detail []}))

(defn reset-recorder
  "This function reset the recorder atom"
  []
  (reset! recorder {:msg [] :detail []}))

(defn update-recorder-msg
  "takes an unfixed error message, and put it into the recorder"
  [inp-message]
  (swap! recorder update-in [:msg] conj inp-message))
  ;(swap! recorder assoc :msg inp-message))

(defn update-recorder-detail
  "takes error message details, and put them into the recorder"
  [inp-message]
  (swap! recorder update-in [:detail] conj inp-message))

(defn modify-errors
  "takes a nREPL response, and returns a message with the errors fixed"
  [inp-message]
  (if (contains? inp-message :err)
    (let [e (session #'*e)]
      (.getCause e))
    inp-message))
;;Exception in thread "nREPL-worker-0" java.lang.IllegalArgumentException: No matching field found: getCause for class clojure.tools.nrepl.middleware$wrap_conj_descriptor$fn__1604


(println "babel.processor loaded")
