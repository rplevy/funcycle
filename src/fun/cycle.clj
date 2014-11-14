(ns fun.cycle)

(defn rotate [coll]
  (conj (vec (rest coll)) (first coll)))

(defn crotate "counterrotate" [coll]
  (cons (last coll) (butlast coll)))

(defn funcycle [fns f]
  (let [fns-atom (atom (crotate fns))
        rotor (fn [& args]
                (swap! fns-atom rotate)
                (apply (first @fns-atom) args))]
    (f rotor)))

(defn reducycle [fns init coll]
  (funcycle fns
            (fn [rotor]
              (reduce rotor init coll))))

(defn mapcycle "almost rhymes with 'popsicle'" [fns coll]
  (funcycle fns
            (fn [rotor]
              (map rotor coll))))

