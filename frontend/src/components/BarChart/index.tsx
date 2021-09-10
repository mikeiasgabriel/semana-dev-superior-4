import axios from "axios";
import { useEffect, useState } from "react";

import Chart from "react-apexcharts";
import { SaleSuccess } from "types/sale";
import { round } from "utils/format";

import { BASE_URL } from "utils/requests";

type SeriesData = {
  name: string;
  data: number[];
}

type ChartData = {
  labels: {
    categories: string[]
  };
  series: SeriesData[]
}

function BarChart() {
  const options = {
    plotOptions: {
      bar: {
        horizontal: true,
      },
    },
  };

  const [chartData, setChartData] = useState<ChartData>({
    labels: {
      categories: [],
    },
    series: [
      {
        name: "",
        data: [],
      },
    ],
  })

  useEffect(() => { 
    axios.get(`${BASE_URL}/sales/success-by-seller`)
      .then(({ data }) => {
        const result = data as SaleSuccess[];
        
        const categories = result.map(r => r.sellerName);
        const series = result.map(r => round((r.deals / r.visited) * 100));
        
        setChartData({
          labels: {
            categories,
          },
          series: [
            {
              name: "% Sucesso",
              data: series,
            },
          ],
        });
      })
      .catch(() => setChartData({
        labels: {
          categories: [],
        },
        series: [
          {
            name: "",
            data: [],
          },
        ],
      })) 
  }, []);

  return <Chart 
      options={{ ...options, xaxis: chartData.labels }} 
      series={chartData.series} 
      type="bar" 
      height="240" 
    />;
}

export default BarChart;
