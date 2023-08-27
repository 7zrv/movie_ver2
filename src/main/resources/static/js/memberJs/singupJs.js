document.addEventListener("DOMContentLoaded", function () {
    const signupForm = document.getElementById("signupForm");
    const signupButton = document.getElementById("signupButton");

    signupButton.addEventListener("click", function () {
        const formData = new FormData(signupForm);
        const formDataJson = {};
        formData.forEach((value, key) => {
            formDataJson[key] = value;
        });

        fetch("/api/member/signupMember", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(formDataJson)
        })
            .then(response => response.json())
            .then(code => {
                if (code === 1) {
                    alert("회원 가입 성공");
                } else {
                    alert("회원 가입 실패: ");
                }
            })
            .catch(error => {
                console.error("Error signing up:", error);
            });
    });
});
