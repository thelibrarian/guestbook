(ns guestbook.models.db
  (:require [clojure.java.jdbc.deprecated :as sql])
  (:import java.sql.DriverManager))

(def db {:classname "org.sqlite.JDBC",
         :subprotocol "sqlite",
         :subname "db.sqlite3"})

(defn create-guestbook-table []
  (sql/with-connection db
    (sql/create-table
     :guestbook
     [:id "INTEGER PRIMARY KEY AUTOINCREMENT"]
     [:timestamp "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"]
     [:name "TEXT"]
     [:message "TEXT"])
    (sql/do-commands "CREATE INDEX timestamp_index ON guestbook (timestamp)")))

(defn create-user-table []
  (sql/with-connection db
    (sql/create-table
     :users
     [:id "TEXT PRIMARY KEY"]
     [:pass "TEXT"]
     [:timestamp "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"])))

(defn get-messages []
  (sql/with-connection db
    (sql/with-query-results res
      ["select name, message, datetime(timestamp, 'localtime') as timestamp from guestbook order by timestamp desc"]
      (doall res))))

(defn save-message [name message]
  (sql/with-connection db
    (sql/insert-values
     :guestbook
     [:name :message]
     [name message])))

(defn add-user-record [user]
  (sql/with-connection db
    (sql/insert-record :users user)))

(defn get-user [id]
  (sql/with-connection db
    (sql/with-query-results res
      ["select * from users where id = ?" id]
      (first res))))
