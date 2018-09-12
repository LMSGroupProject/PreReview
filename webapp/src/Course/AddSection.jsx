import React from "react";

export default class AddSection extends React.Component {
    handleSubmit = (event) => {
        event.preventDefault();
        const data = new FormData(event.target);
        //TODO: change moduleID to match moduleID clicked
        data.append("moduleId", "1");

        for (var pair of data.entries()) {
            console.log(pair[0] + ', ' + pair[1]);
        }

        console.log(event.target.sectionYoutube.value)

        fetch("http://localhost:8080/section/add", {
            method: 'POST',
            body: data
        }).then(function (response) {
            return response.json();
        }).then(function (myJson) {
            console.log(myJson.sectionid);
        });

        // fetch("http://localhost:8080/section/youtube",{
        //     method: 'POST',
        //     body: event.target.sectionYoutube.value
        // });
    };

    render() {
        return (
            <div id="addSectionDiv" className="w3-card">
                <form id="addSectionForm" onSubmit={this.handleSubmit}>
                    <label>Section Name</label>
                    <input name="sectionName" required type="text" placeholder="Enter section name"/>
                    <br/>
                    <label>Section content</label>
                    <textarea name="sectionContent" size="20" type="text" placeholder="Enter section content"/>
                    <br/>
                    <label>Upload video</label>
                    <input type="file"/>
                    <br/>
                    <label>Youtube link</label>
                    <input type="url" name="sectionYoutube" placeholder="Enter Youtube video URL"/>
                    <br/>
                    <input type="submit" value="Add Section"/>
                </form>
            </div>
        )
    }
}