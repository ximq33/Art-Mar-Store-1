import {ReactElement, useState} from "react";
import Nouislider from "nouislider-react";


const Slider = (onSlideFunc): ReactElement => {

    const [selectedVals, setSelectedVals] = useState(60);



    return (<>
        <Nouislider
            range={{min: 60, max: 100}}
            start={[60]}
            step={1}
            connect
            onSlide={(render, handle, value, un, percent) => onSlideFunc}
        />
        <p className="mt-2 mb-0">
            Value:{' '}
            {selectedVals ? (
                <span>{selectedVals}</span>) : null}
        </p>
    </>)
}

export default Slider
