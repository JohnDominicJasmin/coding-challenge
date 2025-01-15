<h1 style="font-size: 36px; font-weight: bold;">Coding Challenge App</h1>
<p>This application demonstrates the retrieval and display of user data in two main features:</p>

<ul>
  <li><strong>List of Users:</strong> Displays a list of users with basic details.</li>
  <li><strong>User Details:</strong> Displays detailed information for a specific user when selected.</li>
</ul>

<h2 style="font-size: 28px; font-weight: bold;">Features</h2>

<h3 style="font-size: 24px; font-weight: bold;">1. List of Users</h3>
<p>The List of Users screen shows a list of users retrieved from a mock API repository. Each user is displayed with the following information:</p>
<ul>
  <li>Full Name</li>
  <li>Address</li>
  <li>Profile Picture</li>
</ul>

<h3 style="font-size: 24px; font-weight: bold;">2. User Details</h3>
<p>The User Details screen shows detailed information for a selected user, including:</p>
<ul>
  <li>Full Name</li>
  <li>Age</li>
  <li>Email Address</li>
  <li>Phone Number</li>
  <li>Address</li>
  <li>Username</li>
  <li>Registration Date</li>
  <li>Profile Picture</li>
</ul>

<h2 style="font-size: 28px; font-weight: bold;">User Flow</h2>
<ul>
  <li><strong>Home Screen:</strong> Shows a list of users.</li>
  <li><strong>Select a User:</strong> Clicking on a user from the list takes you to the User Details screen, displaying their full information.</li>
  <li><strong>Back Button:</strong> From the User Details screen, users can navigate back to the List of Users screen.</li>
</ul>

<h2 style="font-size: 28px; font-weight: bold;">App Architecture</h2>
<p>The app follows a clean architecture with the following components:</p>

<ol>
  <li><strong>Presentation Layer</strong>
    <ul>
      <li><strong>UI Layer:</strong> Composables display user data and provide navigation. It interacts with the ViewModel to present data to the user.</li>
      <li><strong>ViewModel:</strong> Handles the logic to interact with the domain layer and fetch user data. It prepares the data for the UI layer by applying business logic.</li>
    </ul>
  </li>
  <li><strong>Domain Layer</strong>
    <ul>
      <li><strong>Use Cases:</strong> This layer contains the application's business logic, responsible for orchestrating data retrieval and manipulation based on user requests. The use cases interact with the repository layer to fetch data and process it.</li>
      <li><strong>Entities:</strong> Defines the core data objects such as UserModel and UserDetails, which are used throughout the domain layer.</li>
    </ul>
  </li>
  <li><strong>Data Layer</strong>
    <ul>
      <li><strong>Repository:</strong> Acts as a mediator between the domain layer and data sources. It interacts with the mock data source to retrieve user information and return it to the domain layer. The repository abstracts the details of the data retrieval process, allowing the domain layer to focus on business logic.</li>
    </ul>
  </li>
</ol>


<h2 style="font-size: 28px; font-weight: bold;">Installation</h2>
<p>To run this project locally, follow these steps:</p>
<pre><code>git clone https://github.com/JohnDominicJasmin/coding-challenge.git</code></pre>
<p>Open the project in Android Studio.</p>
<p>Build and run the app on an emulator or device.</p>

<h2 style="font-size: 28px; font-weight: bold;">Usage</h2>
<ul>
  <li>When you first launch the app, you'll be presented with a list of users.</li>
  <li>To view a user’s details, simply click on their name or profile picture.</li>
  <li>You can use the back button to return to the list of users from the details screen.</li>
</ul>

<h2 style="font-size: 28px; font-weight: bold;">Testing</h2>
<p>Unit tests are included for the UserRepository with both positive and negative cases, such as:</p>
<ul>
  <li><strong>Success scenarios:</strong> Return of valid user data.</li>
  <li><strong>Failure scenarios:</strong> Handling of errors such as user not found or repository errors.</li>
</ul>
<p>Tests ensure the correctness of the app's functionality when retrieving lists of users and user details.</p>

<h2 style="font-size: 28px; font-weight: bold;">Technologies Used</h2>
<ul>
  <li><strong>Kotlin:</strong> Programming language for Android development.</li>
  <li><strong>Jetpack Compose:</strong> UI toolkit for building Android UIs.</li>
  <li><strong>Hilt:</strong> Dependency injection library for Android.</li>
  <li><strong>Kotlin Coroutines:</strong> For asynchronous operations.</li>
  <li><strong>Flow:</strong> For handling data streams.</li>
</ul>
