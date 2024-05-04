import React, { useState, useEffect } from 'react';
import { Panel, PanelGroup, PanelResizeHandle } from 'react-resizable-panels';
import ChildContainer from './ChildContainer';
import './Layout.css';

const Layout = () => {
    const [data, setData] = useState(null);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await fetch('http://localhost:5432/api/count'); // Replace with your backend URL
            const responseData = await response.json();
            setData(responseData);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    return (
        <div className="container">
            <PanelGroup direction="vertical">
                <Panel>
                    <PanelGroup direction="horizontal" className="hori">
                        <Panel defaultSize={20} minSize={20} maxSize={75}>
                            <ChildContainer number={1} name="child1" />
                        </Panel>
                        <PanelResizeHandle />
                        <Panel defaultSize={50} minSize={20} maxSize={75}>
                            <ChildContainer number={2} name="child2" />
                        </Panel>
                    </PanelGroup>
                </Panel>
                <PanelResizeHandle />
                <Panel defaultSize={30} minSize={20} maxSize={75}>
                    <ChildContainer number={3} name="child3" />
                </Panel>
            </PanelGroup>

            {/* Display backend data */}
            <div>
                {data && (
                    <div>
                        <h2>Backend Data</h2>
                        <p>Add Count: {data.addCount}</p>
                        <p>Update Count: {data.updateCount}</p>
                    </div>
                )}
            </div>
        </div>
    );
};

export default Layout;
