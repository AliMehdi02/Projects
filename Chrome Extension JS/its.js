let output = []
const inputt = document.getElementById("input")
const savee = document.getElementById("save")
const listt = document.getElementById("list")
const deletee = document.getElementById("delete")
const localstrgdata = JSON.parse( localStorage.getItem("output") )
const tabb = document.getElementById("tab")

if (localstrgdata) {
    output = localstrgdata
    render(output)
}

tabb.addEventListener("click", function(){    
    chrome.tabs.query({active: true, currentWindow: true}, function(tabs){
        output.push(tabs[0].url)
        localStorage.setItem("output", JSON.stringify(output) )
        render(output)
    })
})

savee.addEventListener("click", function() {
    output.push(inputt.value)
    inputt.value = ""
    localStorage.setItem("output", JSON.stringify(output) )
    render(output)
})

deletee.addEventListener("click", function() {
    localStorage.clear()
    output = []
    render(output)
})

function render(data) {
    let userdata = ""
    for (let i = 0; i < data.length; i++) {
        userdata += `<li><a target='_blank' href='${data[i]}'>${data[i]}</a></li>`
    }
    listt.innerHTML = userdata
}

