db.createUser(
    {
        user: "demo",
        pwd: "demo",
        roles: [
            {
            role: "readWrite",
            db: "refugee-db"
            }
        ]
    }
)