<template>
    <div>
        <h1>Halls</h1>
        <button @click="showAddHallDialog = true">Add Hall</button>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Seat Count</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="hall in halls" :key="hall.id">
                <td>{{ hall.id }}</td>
                <td>{{ hall.name }}</td>
                <td>{{ hall.seatCount }}</td>
                <td>
                    <button @click="editHall(hall)">Edit</button>
                    <button @click="deleteHall(hall)">Delete</button>
                </td>
            </tr>
            </tbody>
        </table>
        <div v-if="showAddHallDialog">
            <h2>Add Hall</h2>
            <form @submit.prevent="createHall">
                <div>
                    <label>Name:</label>
                    <input v-model="newHall.name" required>
                </div>
                <div>
                    <label>Seat Count:</label>
                    <input v-model.number="newHall.seatCount" required>
                </div>
                <button>Add</button>
                <button type="button" @click="showAddHallDialog = false">Cancel</button>
            </form>
        </div>
        <div v-if="showEditHallDialog">
            <h2>Edit Hall</h2>
            <form @submit.prevent="updateHall">
                <div>
                    <label>Name:</label>
                    <input v-model="currentHall.name" required>
                </div>
                <div>
                    <label>Seat Count:</label>
                    <input v-model.number="currentHall.seatCount" required>
                </div>
                <button>Save</button>
                <button type="button" @click="showEditHallDialog = false">Cancel</button>
            </form>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        data() {
            return {
                halls: [],
                newHall: {
                    name: '',
                    seatCount: 0
                },
                currentHall: {
                    id: null,
                    name: '',
                    seatCount: 0
                },
                showAddHallDialog: false,
                showEditHallDialog: false
            };
        },
        mounted() {
            this.loadHalls();
        },
        methods: {
            loadHalls() {
                axios.get('/api/halls')
                    .then(response => {
                        this.halls = response.data;
                    })
                    .catch(error => {
                        console.error(error);
                    });
            },
            createHall() {
                axios.post('/api/halls', this.newHall)
                    .then(response => {
                        this.halls.push(response.data);
                        this.newHall = {
                            name: '',
                            seatCount: 0
                        };
                        this.showAddHallDialog = false;
                    })
                    .catch(error => {
                        console.error(error);
                    });
            },
            editHall(hall) {
                this.currentHall = Object.assign({}, hall);
                this.showEditHallDialog = true;
            },
            updateHall() {
                axios.put(`/api/halls/${this.currentHall.id}`, this.currentHall)
                    .then(response => {
                        const index = this.halls.findIndex(hall => hall.id === response.data.id);
                        this.halls.splice(index, 1, response.data);
                        this.currentHall = {
                            id: null,
                            name: '',
                            seatCount: 0
                        };
                        this.showEditHallDialog = false;
                    })
                    .catch(error => {
                        console.error(error);
                    });
            },
            deleteHall(hall) {
                if (confirm(`Are you sure to delete hall ${hall.name}?`)) {
                    axios.delete(`/api/halls/${hall.id}`)
                        .then(response => {
                            const index = this.halls.indexOf(hall);
                            this.halls.splice(index, 1);
                        })
                        .catch(error => {
                            console.error(error);
                        });
                }
            }
        }
    };
</script>