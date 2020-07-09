# REST tools collection for Java (RTC4J)

## About this project

At the moment of its conception, this project is for learning purposes. The
idea is for me to experiment with authentic
[**RESTful**](https://www.ics.uci.edu/~fielding/pubs/dissertation/rest_arch_style.htm)
APIs conformed by its three foundational componentes: HTML (Hypermedia), HTTP,
and URI.

To achieve this, I hope for developing a truly RESTful API with no versioning
but still capable of evolving over time, one that offers more than just
[CRUD](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete)
operations without mutating into a
[XML-RPC](https://en.wikipedia.org/wiki/XML-RPC) service, and agnostic of the
chosen data-interchange format.

Additionally, I have the intention to create the bases of a flexible and
adaptable client with a fluent interface for **_browsing_** any RESTful API.
Such a client will be complemented by a domain-specific wrapper, that will be
auto-generated from the API schema, in order to leverage the concrete types and
actions the API exposes in a type-safe way.

Thus, this project will serve as an umbrella for all the tools I hope for
developing with the help of the Java ecosystem in order to achieve the above
goals.

## License

The source code for every single component/tool developed for this project will
be under the [Apache License, Version
2.0](https://www.apache.org/licenses/LICENSE-2.0).
