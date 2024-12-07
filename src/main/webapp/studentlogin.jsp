<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Registration and Login</title>
    <link rel="stylesheet" href="studentlogin.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<body>
    <div id="container" class="container">
        <!-- FORM SECTION -->
        <div class="row">
            <!-- SIGN UP -->
            <div class="col align-items-center flex-col sign-up">
    <div class="form-wrapper align-items-center">
        <div class="form sign-up">
            <form method="post" action="insertstudent" onsubmit="return validatePassword()">
                <div class="input-group">
                    <i class='bx bxs-user'></i>
                    <input type="text" placeholder="ID" name="sid" required>
                </div>
                <div class="input-group">
                    <i class='bx bxs-user'></i>
                    <input type="text" placeholder="Name" name="sname" required>
                </div>
                <div class="input-group">
                    <i class='bx bx-mail-send'></i>
                    <input type="email" placeholder="Email" name="semail" required>
                </div>
                <div class="input-group">
                    <i class='bx bxs-lock-alt'></i>
                    <input type="password" id="spwd" placeholder="Password" name="spwd" required>
                    <small id="password-error" style="color: red; display: none;">Password must contain at least one capital letter, one number, and one special character.</small>
                </div>
                <div class="input-group">
                    <i class='bx bxs-lock-alt'></i>
                    <input type="text" placeholder="Department" name="sdepartment" required>
                </div>
                <div class="input-group">
                    <i class='bx bxs-user'></i>
                    <select name="sgender" placeholder="Gender" required>
                        <option value="" disabled selected>Gender</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                        <option value="Other">Other</option>
                    </select>
                </div>
                <div class="input-group">
                    <i class='bx bxs-calendar'></i>
                    <input type="date" name="sdob" required>
                </div>
                <div class="input-group">
                    <i class='bx bxs-phone'></i>
                    <input type="number" name="scontact" placeholder="Number" required>
                </div>
                <button type="submit" class="btn btn-success">Sign Up</button>
            </form>
            <p>
                <span>Already have an account?</span>
                <b onclick="toggle()" class="pointer">Sign in here</b>
            </p>
        </div>
    </div>
</div>

<script>
    function validatePassword() {
        var password = document.getElementById("spwd").value;
        var regex = /^(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
        if (!regex.test(password)) {
            document.getElementById("password-error").style.display = "block";
            return false;  // Prevent form submission
        }
        document.getElementById("password-error").style.display = "none";
        return true;  // Allow form submission
    }
</script>

            <!-- END SIGN UP -->
            
            <!-- SIGN IN -->
            <div class="col align-items-center flex-col sign-in">
                <div class="form-wrapper align-items-center">
                    <div class="form sign-in">
                        <form method="post" action="checkstudentlogin">
                            <div class="input-group">
                                <i class='bx bxs-user'></i>
                                <input type="email" placeholder="Email" name="semail" required>
                            </div>
                            <div class="input-group">
                                <i class='bx bxs-lock-alt'></i>
                                <input type="password" placeholder="Password" name="spwd" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Sign In</button>
                        </form>
                        <p>
                            <b>Forgot password?</b>
                        </p>
                        <p>
                            <span>Don't have an account?</span>
                            <b onclick="toggle()" class="pointer">Sign up here</b>
                        </p>
                    </div>
                </div>
            </div>
            <!-- END SIGN IN -->
        </div>
        <!-- END FORM SECTION -->

        <!-- CONTENT SECTION -->
        <div class="row content-row">
            <!-- SIGN IN CONTENT -->
            <div class="col align-items-center flex-col">
                <div class="text sign-in">
                    <h2>Welcome Back!</h2>
                </div>
            </div>
            <!-- END SIGN IN CONTENT -->

            <!-- SIGN UP CONTENT -->
            <div class="col align-items-center flex-col">
                <div class="img sign-up"></div>
                <div class="text sign-up">
                    <h2>Join with us</h2>
                </div>
            </div>
            <!-- END SIGN UP CONTENT -->
        </div>
        <!-- END CONTENT SECTION -->
    </div>

    <script src="studentlogin.js"></script>
</body>

</html>
