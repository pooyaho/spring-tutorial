db.courses.insertMany([
    {
        "studentId": "124",
        "id": 1,
        "name": "Maths",
        "unit": 3
    }
])
db.studentDocument.insertOne({age: 17, fatherName: "John"})
db.studentDocument.find({"family": /^A/, "name": {"$ne": "Ali"}})
db.studentDocument.find()
db.studentDocument.deleteMany({age: {"$lt": 20}})

db.getSiblingDB("test").getCollection("studentDocument").aggregate([
    {
        $match: {"family": {$regex: /A/}}
    },
    {
        $group: {
            _id: null,
            "count(*)": {$sum: 1}
        }
    },
    {
        $project: {"c": "$count(*)", "_id": 0}
    }
])

db.getSiblingDB("test").getCollection("studentDocument").aggregate([
    {
        $group: {
            _id: {"family": "$family"},
            "count(*)": {$sum: 1}
        }
    },
    {
        $project: {"f": "$_id.family", "c": "$count(*)", "_id": 0}
    }
])

db.getSiblingDB("test").getCollection("studentDocument").aggregate([
    {
        $group: {
            _id: {"family": "$family"},
            "max(entranceYear)": {$max: "$entranceYear"}
        }
    },
    {
        $project: {"family": "$_id.family", "c": "$max(entranceYear)", "_id": 0}
    }
])

db.getSiblingDB("test").getCollection("studentDocument").aggregate([
    {
        $project: {"s": "$$ROOT", "_id": 0}
    },
    {
        $lookup: {
            localField: "s.studentId",
            from: "courses",
            foreignField: "studentId",
            as: "c"
        }
    },
    {
        $unwind: {
            path: "$c",
            preserveNullAndEmptyArrays: false
        }
    },
    {
        $replaceRoot: {
            newRoot: {$mergeObjects: ["$s", "$c", "$$ROOT"]}
        }
    },
    {
        $project: {"s": 0, "c": 0}
    }
])

db.getSiblingDB("test").getCollection("studentDocument").find({"studentId": {$nin: ["123", "124"]}})
db.studentDocument.updateMany({"family": {"$eq": "Alavi"}}, {"$unset": {"fatherName": ""}})
db.studentDocument.createIndex({"name": 1}, {unique: true})
db.studentDocument.getIndexes()