(ns doublets.solver
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def words (-> "words.edn"
               (io/resource)
               (slurp)
               (read-string)))


(defn is-link? [word1 word2]
  (if-not (= (count word1) (count word2))
    nil
    (some true? (for [i (range (count word1))
                      :let [word1' (apply str (assoc (vec word1) i "_"))
                            word2' (apply str (assoc (vec word2) i "_"))]]
                  (= word1' word2')))))

(defn link-fn [word1]
  (fn [word2] (is-link? word1 word2)))

(defn search [word1 word2 path]
  (first (let [candidate-paths (->> words
                                    (filter (link-fn word1))
                                    (remove (set path)))]
           (for [next-hop candidate-paths]
             (if (= next-hop word2)
               (concat path [next-hop])
               (search next-hop word2 (concat path [next-hop])))))))

(defn doublets [word1 word2]
  (search word1 word2 [word1]))
