(ns magic-square.puzzle)

(def values [1.0 1.5 2.0 2.5 3.0 3.5 4.0 4.5 5.0])

(defn all-different [coll]
  (= (count coll) (count (set coll))))

(defn sum-rows [square]
  (map #(apply + %) square))

(defn sum-cols [square]
  [(apply + (map first square))
   (apply + (map second square))
   (apply + (map last square))])

(defn sum-diag [square]
  [(+ (get-in square [0 0]) (get-in square [1 1]) (get-in square [2 2]))
   (+ (get-in square [0 2]) (get-in square [1 1]) (get-in square [2 0]))])

(defn check-magic [square]
  (and (apply = (sum-rows square))
       (apply = (sum-cols square))
       (apply = (sum-diag square))))

(defn magic-square [values]
  (for [a values
        b values
        c values
        d values
        e values
        f values
        g values
        h values
        i values
        :when (all-different [a b c d e f g h i])
        :let [square [[a b c] [d e f] [g h i]]]
        :when (check-magic square)]
    square))
