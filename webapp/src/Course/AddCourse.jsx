import React from "react";

export default class AddCourse extends React.Component {
    handleSubmit = (event) => {
        // event.preventDefault();
        if (event.target.courseName.value.length !== 0 &&
            event.target.courseDescription.value.length !== 0) {
            const data = new FormData(event.target);
            data.append("trainerId", "1");

            console.log(event.target.courseName.value.length);
            fetch("http://localhost:8080/course/add", {
                method: 'POST',
                body: data
            });
        } else {
            event.preventDefault();
            document.getElementById("noContinue").innerHTML = "Need to fill in all input";
        }
    };

    render() {
        return (
            <div id="addCourseDiv" className="w3-card">
                <form id="addCourseForm" onSubmit={this.handleSubmit}>
                    <label>Course Name</label>
                    <input name="courseName" type="text" placeholder="Enter course name"/>
                    <br/>
                    <label>Course description</label>
                    <input name="courseDescription" type="text" placeholder="Enter course description"/>
                    <br/>
                    <div id="noContinue"></div>
                    <input type="submit" value="Add Course"/>
                </form>
            </div>
        )
    }
}
