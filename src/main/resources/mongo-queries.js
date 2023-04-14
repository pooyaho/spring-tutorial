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

db.student_collection.insertMany([
    {"name": "Student 1", "_id": 1},
    {"name": "Student 2", "_id": 2},
    {"name": "Student 3", "_id": 3}
]);

db.course_collection.insertMany([
    {"name": "Course 1", "_id": 1},
    {"name": "Course 2", "_id": 2},
    {"name": "Course 3", "_id": 3}
]);

db.st_co.drop();

db.st_co.insertMany([
    {"st_id": 1, "co_id": 1},
    {"st_id": 1, "co_id": 2},
    {"st_id": 2, "co_id": 1},
    {"st_id": 3, "co_id": 2}
]);

db.getSiblingDB("second_class").getCollection("course_collection").aggregate([
    {
        $project: {"cc": "$$ROOT", "_id": 0}
    },
    {
        $lookup: {
            localField: "cc._id",
            from: "st_co",
            foreignField: "co_id",
            as: "st_co"
        }
    },
    {
        $unwind: {
            path: "$st_co",
            preserveNullAndEmptyArrays: false
        }
    },
    {
        $project: {"_id": "$cc._id", "co_id": "$st_co.co_id"}
    }
]);
let courseIds = [];
db.st_co.find({}, {"_id": 0, "st_id": 0}).forEach((item) => {
    courseIds.push(item.co_id)
})
print(courseIds)
db.getSiblingDB("second_class").getCollection("course_collection").find({"_id": {$nin: courseIds}})

for (let i = 4; i <= 100; i++) {
    if (i % 3 === 1) {
        for (let j = 1; j < 30; j++) {
            db.st_co.insertMany([
                {"st_id": i, "co_id": j},
            ]);
        }
    }
}

db.student_collection.find({})


db.getSiblingDB("second_class").getCollection("student_collection").aggregate([
    {
        $project: {"s": "$$ROOT", "_id": 0}
    },
    {
        $lookup: {
            localField: "s._id",
            from: "st_co",
            foreignField: "st_id",
            as: "sc"
        }
    },
    {
        $unwind: {
            path: "$sc",
            preserveNullAndEmptyArrays: false
        }
    },
    {
        $group: {
            _id: {"s᎐_id": "$s._id"},
            "count": {$sum: 1}
        }
    },
    {
        $match: {$expr: {$gt: ["$count", 20]}}
    },
    {
        $sort: {"_id.s᎐_id": 1}
    },
    {
        $project: {"_id": "$_id.s᎐_id", "count(*)": "$count"}
    }
])