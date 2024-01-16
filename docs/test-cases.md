# Acceptance Test List

## Sales Manager:

### 1. User Registration and Authentication:

| Test Case                             | Input                                       | Expected Result                            |
|----------------------------------------|---------------------------------------------|--------------------------------------------|
| Test Successful Registration           | Valid email and password                    | Sales Manager successfully registers.      |
| Test Successful Login                  | Registered email and password               | Sales Manager successfully logs in.        |
| Test Registration with Invalid Email    | Invalid email format                        | Registration fails with an error message.  |
| Test Login with Incorrect Password     | Correct email, incorrect password           | Login fails with an error message.          |

### 2. Ad Creation and Posting (for Selling Products):

| Test Case                             | Input                                       | Expected Result                            |
|----------------------------------------|---------------------------------------------|--------------------------------------------|
| Test Successful Ad Creation            | Valid title, description, category, images   | Ad is successfully created and visible.   |
| Test Preview Before Posting            | Ad details                                  | Sales Manager can preview the ad.          |
| Test Ad Creation with Missing Title     | Missing title                               | Ad creation fails with an error message.   |
| Test Ad Creation with Invalid Images    | Images in an unsupported format             | Ad creation fails with an error message.   |

### 3. Ad Search and Filtering (for Buying Source Components):

| Test Case                             | Input                                       | Expected Result                            |
|----------------------------------------|---------------------------------------------|--------------------------------------------|
| Test Search by Keyword                  | Relevant keyword                            | Ads related to the keyword are displayed. |
| Test Filter by Date                     | Date filter criteria                        | Ads are filtered based on the date range.  |
| Test Empty Search Query                 | Empty search query                          | Error message indicating the need for a query. |
| Test Invalid Date Format                | Invalid date format in the filter           | Error message indicating an invalid format. |

### 4. Ad Details and Interaction (for Buying Source Components):

| Test Case                             | Input                                       | Expected Result                            |
|----------------------------------------|---------------------------------------------|--------------------------------------------|
| Test View Ad Details                    | Click on an ad                               | Detailed information is displayed.        |
| Test Express Interest                   | Click on "Express Interest"                  | Interest is expressed, notification sent. |
| Test Contacting with Empty Message      | Click on "Contact" with empty message        | Contact fails with an empty message error.|
| Test Marking as Favorite without Logging In | Attempt without logging in              | Fails with a prompt to log in.              |

### 5. Ad Management (for Selling Products):

| Test Case                             | Input                                       | Expected Result                            |
|----------------------------------------|---------------------------------------------|--------------------------------------------|
| Test Edit Ad Details                    | Modify ad details                           | Ad details are successfully edited.       |
| Test Deactivate Ad                      | Click on "Deactivate"                       | Ad is deactivated and temporarily hidden. |
| Test Delete Ad without Confirmation     | Click on "Delete" without confirmation      | Deletion fails, prompting for confirmation.|
| Test Deactivate Ad without Logging In   | Attempt without logging in                  | Deactivation fails with a prompt to log in.|

### 6. Notifications:

| Test Case                             | Input                                       | Expected Result                            |
|----------------------------------------|---------------------------------------------|--------------------------------------------|
| Test Receive New Message Notification  | New message received                        | Notification received for new message.    |
| Test Customize Notifications           | Modify notification settings                | Notification settings are customized.     |
| Test Receive Notification with Disabled Settings | Disable all notifications           | No notifications are received.             |
| Test Invalid Notification Setting      | Provide an invalid setting                  | Fails with an error message.               |

---

## Engineer:

### 1. User Registration and Authentication:

| Test Case                             | Input                                       | Expected Result                            |
|----------------------------------------|---------------------------------------------|--------------------------------------------|
| Test Successful Registration           | Valid email and password                    | Engineer successfully registers.           |
| Test Successful Login                  | Registered email and password               | Engineer successfully logs in.             |
| Test Registration with Invalid Email    | Invalid email format                        | Registration fails with an error message.  |
| Test Login with Incorrect Password     | Correct email, incorrect password           | Login fails with an error message.         |

### 2. Ad Search and Filtering (for Ordering Source Components):

| Test Case                             | Input                                       | Expected Result                            |
|----------------------------------------|---------------------------------------------|--------------------------------------------|
| Test Search by Keyword                  | Relevant keyword                            | Ads related to the keyword are displayed. |
| Test Filter by Date                     | Date filter criteria                        | Ads are filtered based on the date range.  |
| Test Empty Search Query                 | Empty search query                          | Error message indicating the need for a query. |
| Test Invalid Date Format                | Invalid date format in the filter           | Error message indicating an invalid format.|

### 3. Ad Details and Interaction (for Ordering Source Components):

| Test Case                             | Input                                       | Expected Result                            |
|----------------------------------------|---------------------------------------------|--------------------------------------------|
| Test View Ad Details                    | Click on an ad                               | Detailed information is displayed.        |
| Test Express Interest                   | Click on "Express Interest"                  | Interest is expressed, notification sent. |
| Test Contacting with Empty Message      | Click on "Contact" with empty message        | Contact fails with an empty message error.|
| Test Marking as Favorite without Logging In | Attempt without logging in              | Fails with a prompt to log in.              |

### 4. User Profiles:

| Test Case                             | Input                                       | Expected Result                            |
|----------------------------------------|---------------------------------------------|--------------------------------------------|
| Test Customize Profile                  | Add profile picture, bio, and contact details | Engineer's profile is successfully customized. |
| Test View Ordered Source Components    | Click on "Ordered Source Components"        | Ordered source components are displayed.  |
| Test Customize Profile without Logging In | Attempt without logging in                | Fails with a prompt to log in.              |
| Test View Ordered Source Components with Empty List | Empty list                             | Message indicating no ordered components.|

### 5. Notifications:

| Test Case                             | Input                                       | Expected Result                            |
|----------------------------------------|---------------------------------------------|--------------------------------------------|
| Test Receive New Message Notification  | New message received                        | Notification received for new message.    |
| Test Customize Notifications           | Modify notification settings                | Notification settings are customized.     |
| Test Receive Notification with Disabled Settings | Disable all notifications           | No notifications are received.             |
| Test Invalid Notification Setting      | Provide an invalid setting                  | Fails with an error message.               |
