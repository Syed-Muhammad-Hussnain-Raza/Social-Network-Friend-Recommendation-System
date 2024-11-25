# Social Network Graph System

## Project Overview
This project models a social network using graph theory. Each **user** in the network is represented as a **node** (vertex), and **friend relationships** are represented as **edges**. The system enables various social interactions like adding or removing friends, finding mutual friends, and suggesting potential friends based on common connections. Additionally, the system integrates a **user posting system** to simulate the activity feed seen on social platforms.

## Key Features and Functions

1. **Add Friends**
    - Users can create friendships by connecting their nodes. This action adds an edge between the two nodes in the graph.

2. **Delete Friends**
    - Users can break existing friendships, which removes the edge between two nodes in the graph.

3. **Display Mutual Friends**
    - This function allows users to find the mutual friends they share with another user. The system traverses the graph to find common neighbors between two nodes.

4. **Suggest Potential Friends Based on Common Connections**
    - The system recommends **friends-of-friends**, suggesting users who are likely to have shared interests and be open to forming new connections. This is done by analyzing second-degree connections in the graph.

5. **User Posting System**
    - Users can make posts, which are stored and displayed in a **first-come, first-served (FCFS)** approach, similar to a **queue-based** structure. This helps in creating a real-time activity feed for each user.

## Technologies Used
- **Graph Theory**: Used to represent users and their relationships in the social network.
- **Java (IntelliJ IDEA)**: The main programming language for back-end logic and algorithm implementation.
- **JavaFX**: A graphical user interface (GUI) framework for building the front-end of the application.
- **MySQL**: Used for storing user data, friendships, and posts in a relational database for persistence.

## Architecture
The system follows a **client-server** architecture with the following components:
- **Back-end**: Implements the graph structure using Java, allowing for operations such as adding/removing friends and displaying mutual connections.
- **Front-end**: Built using **JavaFX** to provide an interactive UI, where users can add friends, make posts, and view recommendations.
- **Database**: A **MySQL** database stores the users, friendships, and posts for persistent storage, allowing the system to maintain a long-term record of user interactions.

## Setup and Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Syed-Muhammad-Hussnain-Raza/Social-Network-Friend-Recommendation-System.git
   cd Social-Network-Friend-Recommendation-System
   ```
2. **Setup MySQL Database:**
   
   * Create a database called ```social_network```.<br>
   * Run the SQL scripts in the ```/database/``` folder to set up the tables for users, friendships, and posts.

3. **Run the Project:**
   - Open the project in IntelliJ IDEA.
   - Run the main application file to start the front-end (JavaFX) and back-end (GraphTheory).

## Future Improvements
- Implement privacy settings and user profiles.
- Enhance the post system with like and comment features.
- Integrate with a real-time notification system for new friend requests or posts.

## License 
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contributors
* [***Syed Muhammad Hussnain Raza***](https://www.linkedin.com/in/syed-muhammad-hussnain-raza/)
* ***Muhammad Fasih Ur Rehman***
* ***Aneeq Ur Rehman***