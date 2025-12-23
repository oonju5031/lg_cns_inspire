import {useState, useEffect} from 'react';
import api from "../../api/axios"

const BlogJsonPage = () => {
    console.log(">>> BlogJsonPage load event");

    const [data,setData] = useState([]);

    const getBlogs = async () => {
        const response = await api.get("/blogs");
        setData(response.data);

        console.log(data);
        console.log(data[0].title);
    }

    useEffect(() => {
        getBlogs();
    }, []);

    return (
        <div>
            데이터: {data[0]?.title}
        </div>
    );
}

export default BlogJsonPage;