const url = "http://localhost:8080/task/user/1";

function hideloading() {

    document.getElementById("loading").style.display = "none";
}

function show(tasks) {

    let tab = `<thead>
        
        <th scope="cool">#</th>
        <th scope="cool">description</th>
        <th scope="cool">username</th>
        <th scope="cool">user_id</th>

    </thead>
    
    `;

    for (let task of tasks) {

        tab += `
            <tr>
                <td scope="cool">${task.id}</td>
                <td>${task.description}</td>
                <td>${task.user.username}</td>
                <td>${task.user.id}</td>
            </tr>
        `;
    }

    document.getElementById("tasks").innerHTML = tab
}

async function getApi(url) {

    const response = await fetch(url,{
        method: "GET"
    });

    var data = await response.json();
    console.log(data);

    if (response) 
        hideloading();
    show(data)
   
};

getApi(url)