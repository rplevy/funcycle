(ns fun.cycle)

(defn rotate [coll]
  (conj (vec (rest coll)) (first coll)))

(defn crotate "counterrotate" [coll]
  (cons (last coll) (butlast coll)))

(defn funcycle [fns body-fn]
  (let [fns-atom (atom (crotate fns))
        rotor (fn [& args]
                (swap! fns-atom rotate)
                (apply (first @fns-atom) args))]
    (body-fn rotor)))

(defn reducycle
  ([fns init coll]
     (funcycle fns
               (fn [rotor]
                 (reduce rotor init coll))))
  ([fns coll]
     (funcycle fns
               (fn [rotor]
                 (reduce rotor coll)))))

(defn mapcycle "almost rhymes with 'popsicle'" [fns & colls]
  (funcycle fns
            (fn [rotor]
              (apply map rotor colls))))

