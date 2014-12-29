# Guestbook

This is the initial exercise app from the book [Web Development with Clojure][1]
by [Dmitri Sotnikov][2]. This mainly sticks to the code in the book, but
I have made a few customisations to my liking.

One thing to note is that the `clojure.java.jdbc` API changed quite
significantly between version 0.2 (which is used in the book), and version
0.3 (which is current as of this writing), but the old API is still
available in the `clojure.java.jdbc.deprecated` namespace, which is what
I have referenced in the `guestbook.models` namespace.

[1]: https://pragprog.com/book/dswdcloj/web-development-with-clojure
[2]: http://yogthos.net/

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## License

Copyright © 2014 Tony Kemp
