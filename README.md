Elasticsearch Integration with MySQL
This project demonstrates how to set up and use Elasticsearch with a MySQL database to perform advanced search operations. The main goal is to provide a unified search across multiple tables such as categories, brands, and products. By indexing MySQL data into Elasticsearch, users can efficiently query the data using Elasticsearch's powerful search capabilities.

Features
Elasticsearch Setup: Configure Elasticsearch to index data from MySQL tables.
Full-Text Search: Perform full-text searches across multiple fields like product names, descriptions, and categories.
Auto-Suggestions: Implement auto-suggestions as users type in the search bar.
Fuzzy Search: Handle typos and partial matches in search queries using fuzzy search.
Pagination and Filtering: Support for pagination and filtering of search results.
Real-Time Search Updates: Keep Elasticsearch indexes in sync with MySQL data updates.
Technologies
Java (Spring Boot)
Elasticsearch: For indexing and searching data.
MySQL: Relational database for storing original data.
How to Run
Set up MySQL database with the necessary tables (categories, brands, products).
Install and configure Elasticsearch on your system.
Configure your application to connect to both MySQL and Elasticsearch.
Run the application to start indexing data from MySQL into Elasticsearch.
Access the search endpoint to perform queries.
Future Improvements
Add support for more complex search queries like aggregations and custom analyzers.
Implement additional features for syncing data between MySQL and Elasticsearch in real time.
