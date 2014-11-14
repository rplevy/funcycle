(ns red.ucycle)

(defn rotate [coll]
  (conj (vec (rest coll)) (first coll)))

(defn crotate "counterrotate" [coll]
  (cons (last coll) (butlast coll)))

(defn reducycle [fns init coll]
  (let [fns-atom (atom (crotate fns))
        rotor (fn [& args]
                (swap! fns-atom rotate)
                (apply (first @fns-atom) args))]
    (reduce rotor init coll)))
