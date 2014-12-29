(ns guestbook.routes.home
  (:require [compojure.core :refer :all]
            [guestbook.views.layout :as layout]
            [hiccup.form :refer :all]))

(defn show-guests []
  [:ul.guests
   (for [{:keys [message name timestamp]}
         [{:message "Hello" :name "Bob" :timestamp nil}
          {:message "Foo" :name "Bar" :timestamp nil}]]
     [:li
      [:blockquote message]
      [:p "- " [:cite name]]
      [:time timestamp]])])

(defn home [& [name message error]]
  (layout/common
    [:h1 "Guestbook"]
    [:p "Welcome to my guestbook"]
    [:p error]
    (show-guests)
    [:hr]
    (form-to [:post "/"]
      [:p "Name:"]
      (text-field "name" name)
      [:p "Message:"]
      (text-area {:rows 10 :cols 40} "message" message)
      [:br]
      (submit-button "comment"))))

(defroutes home-routes
  (GET "/" [] (home)))
