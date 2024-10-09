# TasksApp

TasksApp is a simple task management application for Android, developed using Kotlin. The goal of this app is to allow users to easily create, edit, and update their tasks. The project follows the MVVM (Model-View-ViewModel) architecture pattern and uses Jetpack Navigation for screen navigation.

## Features

- **Task List**: Displays a list of all created tasks.
- **Edit Task**: Allows the user to edit an existing task.
- **Update Task**: After editing a task, the app automatically navigates back to the task list.
- **Data Persistence**: Tasks are stored locally using SQLite via Room.

## Technologies Used

- **Kotlin**: The main language used for development.
- **MVVM**: Architecture pattern to separate concerns.
- **Room**: Persistence library used to manage SQLite database access.
- **Jetpack Navigation**: Handles navigation between fragments.
- **Data Binding**: For binding views with the layout.
- **LiveData**: To observe changes in data in the ViewModel.
- **Fragments**: Each screen in the app is a Fragment managed by Jetpack Navigation.

## Project Structure

- `data/`: Contains the Room database and the repository.
  - **TaskDatabase**: Configures the Room database.
  - **TaskRepository**: Layer responsible for managing data operations.
  
- `ui/`: Contains fragments and user interface logic.
  - **TasksFragment**: Displays the task list.
  - **EditTaskFragment**: Screen to edit an existing task.

- `viewmodel/`: Contains ViewModels responsible for maintaining the logic and state data.
  - **EditTaskViewModel**: Manages the logic for editing and updating tasks.

## Installation

1. Clone this repository:
   ```bash
   git clone https://github.com/rafaelkenedy/tasks-app.git