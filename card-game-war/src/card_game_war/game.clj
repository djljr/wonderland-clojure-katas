(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    {:suit suit :rank rank}))

(defn card-str [card]
  (let [suit-str {:spade "S" :club "C" :diamond "D" :heart "H"}
        rank-str {2 "2" 3 "3" 4 "4" 5 "5" 6 "6" 7 "7" 8 "8" 9 "9" 10 "T" :jack "J" :queen "Q" :king "K" :ace "A"}]
    (str (get rank-str (:rank card)) (get suit-str (:suit card)))))

(defn score [coll value]
  (.indexOf coll value))

(defn play-round [player1-card player2-card]
  (let [player1-rank-score (score ranks (:rank player1-card))
        player2-rank-score (score ranks (:rank player2-card))
        spoils [player1-card player2-card]]
    (cond
      (= player1-rank-score player2-rank-score)
      (let [player1-suit-score (score suits (:suit player1-card))
            player2-suit-score (score suits (:suit player2-card))]
        (if (< player1-suit-score player2-suit-score)
          [[] (reverse spoils)]
          [spoils []]))
      (< player1-rank-score player2-rank-score)
      [[] (reverse spoils)]
      (> player1-rank-score player2-rank-score)
      [spoils []])))

(defn play-game [player1-cards player2-cards]
  (loop [turn 0
         player1-cards' player1-cards
         player2-cards' player2-cards]
    (cond
      (= 0 (count player1-cards'))
      (str "Player 2 wins: " turn " turns")
      (= 0 (count player2-cards'))
      (str "Player 1 wins: " turn " turns")
      :else
      (let [p1-card (first player1-cards')
            p2-card (first player2-cards')
            [new-p1-cards new-p2-cards] (play-round p1-card p2-card)]
        (recur (inc turn)
               (concat (rest player1-cards') new-p1-cards)
               (concat (rest player2-cards') new-p2-cards))))))
